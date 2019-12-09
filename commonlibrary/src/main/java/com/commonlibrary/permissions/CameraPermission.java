package com.commonlibrary.permissions;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.commonlibrary.R;
import com.commonlibrary.listeners.IDialogListener;
import com.commonlibrary.util.Utils;

/**
 * Created by    on 4/20/2017.
 */

public class CameraPermission extends ResourcePermission
	{

		public static final int PERMISSION_REQUEST_CODE_CAMERA = 242;

		public CameraPermission( Activity context )
			{
				super(context);
			}

		@Override
		public void Request()
			{
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
					{
						if ((ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
								|| (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
								|| (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED))
							{

								String[] permissions = new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

								ActivityCompat.requestPermissions(getContext(), permissions, PERMISSION_REQUEST_CODE_CAMERA);

								return;
							}

						grantPermission(true);
					}
				else
					{
						grantPermission(true);
					}

			}

		@Override
		public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
			{
				switch (requestCode)
					{
					case PERMISSION_REQUEST_CODE_CAMERA:
						{
							// If request is cancelled, the result arrays are empty.
							if (grantResults.length > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED) && (grantResults[1] == PackageManager.PERMISSION_GRANTED) && (grantResults[2] == PackageManager.PERMISSION_GRANTED))
								{

									grantPermission(true);

								}
							else
								{

									if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
										{
											if (permissions != null && permissions.length > 0)
												{
													boolean showRationale = getContext().shouldShowRequestPermissionRationale(permissions[0]);
													if (!showRationale)
														{
															// user also CHECKED "never ask again"
															// you can either enable some fall back,
															// disable features of your app
															// or open another dialog explaining
															// again the permission and directing to
															// the app setting

															Utils.getInstance().showDialog(getContext(), getContext().getString(R.string.storagepermission), getContext().getString(R.string.permission_needed_writestorage),
																	getContext().getString(R.string.yes), getContext().getString(R.string.cancel1), new IDialogListener()
																		{
																			@Override
																			public void onClickOk(boolean isok)
																				{

																					if (isok)
																						{
																							Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
																							Uri uri = Uri.fromParts("package", getContext().getPackageName(), null);
																							intent.setData(uri);
																							getContext().startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
																						}
																				}
																		});

														}
													else if (Manifest.permission.CAMERA.equals(permissions[0]))
														{

															// showRationale(permissions[0], R.string.permission_denied_writestorage);
															// user did NOT check "never ask again"
															// getContext() is a good place to explain the user
															// why you need the permission and ask if he wants
															// to accept it (the rationale)

															Utils.getInstance().showDialog(getContext(), getContext().getString(R.string.storagepermission), getContext().getString(R.string.permission_denied_writestorage),
																	getContext().getString(R.string.retry), getContext().getString(R.string.cancel1), new IDialogListener()
																		{
																			@Override
																			public void onClickOk(boolean isok)
																				{
																					String[] permissions = new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION};

																					ActivityCompat.requestPermissions(getContext(), permissions, PERMISSION_REQUEST_CODE_CAMERA);

																				}
																		});

														}
												}
										}
								}
						}
						break;
					}
			}

	}
